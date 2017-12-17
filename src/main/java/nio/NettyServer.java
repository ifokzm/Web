package nio;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class NettyServer
{
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    
    private Channel channel;
    
    private EventLoopGroup bossGroup;
    
    private EventLoopGroup workerGroup;
    
    @Value("${nio.host:127.0.0.1}")
    String host = "192.168.1.8";
    
    @Value("${nio.port:9999}")
    int port = 9999;
    
    @Value("${nio.ioThreadNum:5}")
    int ioThreadNum = 5;
    
    @Value("${nio.backlog:1024}")
    int backlog = 1024;
    
    /**
     * @throws InterruptedException
     */
    @PostConstruct
    public void start()
        throws InterruptedException
    {
        logger.info("begin to start rpc server");
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(ioThreadNum);
        
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, backlog)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childHandler(new ChannelInitializer<SocketChannel>()
            {
                @Override
                protected void initChannel(SocketChannel socketChannel)
                    throws Exception
                {
                    socketChannel.pipeline()
                        // .addLast("decoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                        // .addLast("encoder", new LengthFieldPrepender(4, false))
                        .addLast(new ChannelInboundHandlerAdapter()
                        {
                            
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg)
                                throws Exception
                            {
                                ByteBuf buf = (ByteBuf)msg;
                                byte[] bs = new byte[buf.readableBytes()];
                                buf.readBytes(bs);
                                System.out.println(new String(bs));
                            }
                        });
                    // .addLast(new RpcDecoder(RpcRequest.class))
                    // .addLast(new RpcEncoder(RpcResponse.class))
                }
            });
        
        channel = serverBootstrap.bind(host, port).sync().channel();
        
        logger.info("NettyRPC server listening on port " + port + " and ready for connections...");
    }
    
    @PreDestroy
    public void stop()
    {
        logger.info("destroy server resources");
        if (null == channel)
        {
            logger.error("server channel is null");
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
        bossGroup = null;
        workerGroup = null;
        channel = null;
    }
    
}
