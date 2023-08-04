package com.nettyServer.demo.Handler.Server;

import com.nettyServer.demo.Entity.RequestData;
import com.nettyServer.demo.Entity.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf tmp;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        System.out.println("Handler added");
        tmp=ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        System.out.println("Handler removed");
        tmp.release();
        tmp=null;
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf mess=(ByteBuf) msg;
        tmp.writeBytes(mess);
        mess.release();

        //process request
        if(tmp.readableBytes()>=4){
            RequestData requestData=new RequestData();
            requestData.setIntValue(tmp.readInt());

            ResponseData responseData=new ResponseData();
            responseData.setIntValue(requestData.getIntValue()*2);

            ChannelFuture future=ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
