package com.nettyServer.demo.Handler.Client;

import com.nettyServer.demo.Entity.RequestData;
import com.nettyServer.demo.Entity.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        RequestData msg=new RequestData();
        msg.setIntValue(666);
        msg.setStringValue("The activation of channel");
        ChannelFuture future=ctx.writeAndFlush(msg);
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println((ResponseData) msg);
        ctx.close();
    }
}
