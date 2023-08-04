package com.nettyServer.demo.Service;

import com.nettyServer.demo.Handler.Client.ClientHandler;
import com.nettyServer.demo.Handler.Client.RequestDataEncoder;
import com.nettyServer.demo.Handler.Client.ResponseDataDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws Exception{
        String host="localhost";
        int port=8080;
        EventLoopGroup workerGroup=new NioEventLoopGroup();

        try{
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(workerGroup)
                     .channel(NioSocketChannel.class)
                     .option(ChannelOption.SO_KEEPALIVE,true)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         public void initChannel(SocketChannel ch) throws Exception{
                             ch.pipeline().addLast(new RequestDataEncoder(),new ResponseDataDecoder(),new ClientHandler());
                         }
                     });
            ChannelFuture future=bootstrap.connect(host,port).sync();
            future.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
