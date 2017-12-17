package nio;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient
{
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    
    EventLoopGroup group = new NioEventLoopGroup();
    
    Bootstrap b;
    
    public void connect(int port, String host)
    {
        b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(
            new ChannelInitializer<SocketChannel>()
            {
                protected void initChannel(SocketChannel sc)
                    throws Exception
                {
                    sc.pipeline().addLast(new ChannelInboundHandlerAdapter()
                    {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg)
                            throws Exception
                        {
                            System.out.println(msg);
                        }
                        
                        @Override
                        public void handlerAdded(ChannelHandlerContext ctx)
                            throws Exception
                        {
                            System.out.println("connection");
                        }
                        
                        @Override
                        public void handlerRemoved(ChannelHandlerContext ctx)
                            throws Exception
                        {
                            System.out.println("disconnection");
                            executor.execute(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    try
                                    {
                                        System.out.println("reconnection");
                                        TimeUnit.SECONDS.sleep(5);
                                        connect(9999, "192.168.1.8");
                                    }
                                    catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            
                        }
                        
                    });
                }
            });
        
        try
        {
            ChannelFuture future = b.connect(new InetSocketAddress(host, port)).sync();
            // future.channel().closeFuture().sync(); // 阻塞主线程
            future.channel().closeFuture().addListener(new ChannelFutureListener()
            {
                @Override
                public void operationComplete(ChannelFuture arg0)
                    throws Exception
                {
                    System.out.println("disconnection");
                }
            });
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
        }
    }
    
}
