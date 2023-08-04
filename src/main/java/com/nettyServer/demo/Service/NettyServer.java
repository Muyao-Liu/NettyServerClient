package com.nettyServer.demo.Service;

import com.nettyServer.demo.Handler.Server.ProcessingHandler;
import com.nettyServer.demo.Handler.Server.RequestDecoder;
import com.nettyServer.demo.Handler.Server.ResponseDataEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    private int port;
    public NettyServer(int port){
        this.port=port;
    }

    public static void main(String[] args) throws Exception{
        //get or set default port as 8080
        int port= args.length>0?Integer.parseInt(args[0]):8080;
        new NettyServer(port).run();
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         public void initChannel(SocketChannel ch) throws Exception{
                             ch.pipeline().addLast(new RequestDecoder(),new ResponseDataEncoder(),new ProcessingHandler());
                         }
                     }).option(ChannelOption.SO_BACKLOG,128)//max number of connections
                       .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture future=bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
