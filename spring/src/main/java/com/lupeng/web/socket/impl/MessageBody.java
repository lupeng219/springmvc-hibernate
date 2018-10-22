package com.lupeng.web.socket.impl;
import com.lupeng.web.socket.MsgField;
import com.lupeng.web.socket.MsgPiece;
import com.lupeng.web.socket.MsgField.FillSide;
/**
 * Created by lupeng on 2018/8/21.
 */
public class MessageBody extends MsgPiece {

        private static final MsgField[] items = new MsgField[]{
                new MsgField("version", 4, '0',FillSide.RIGHT),
                new MsgField("user_id", 4, '0',FillSide.RIGHT),
                new MsgField("authenticator_Md5", 16, '0',FillSide.RIGHT),
                new MsgField("version", 4, '0',FillSide.RIGHT),
        };

        public MessageBody() {
            super(items);
        }

        private Integer version;

        private Integer user_id;

        private String authenticator_Md5;

        private Integer Timestamp;

    public Integer getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        Timestamp = timestamp;
    }

    public String getAuthenticator_Md5() {
        return authenticator_Md5;
    }

    public void setAuthenticator_Md5(String authenticator_Md5) {
        this.authenticator_Md5 = authenticator_Md5;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
