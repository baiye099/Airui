package com.iweb.kasabury.app;

public class KasaburyContact {
    /*    名字（firstName）
        姓氏（lastName）
        电话号码（phoneNumber，6-14位）
        聊天历史（chatHistory，最多20条消息）*/
    private String firstName;//名字（firstName）
    private String lastName; //姓氏（lastName）
    private String phoneNumber;//电话号码（phoneNumber，6-14位）
    private String[] chatHistory; //聊天历史（chatHistory，最多20条消息）
    private int messageCount;//消息数量，默认消息数量是0

    //  无参构造方法
    public KasaburyContact() {
    }
//    有参构造方法
/*    @param firstName  名字（不能为空）
    @param lastName  名字（不能为空）
    @param phoneNumber  名字（不能小于6为或者大于14位）
    @return
    */

    public KasaburyContact(String firstName, String lastName, String phoneNumber) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("firstName cannot be null or  empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("firstName cannot be null or  empty");
        }
        if (phoneNumber == null || phoneNumber.length() > 14 || phoneNumber.length() < 6) {
            throw new IllegalArgumentException("phoneNumber must be between and 14!");
        }
        //走到下面说明上面符合要求
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        //初始化消息记录
        this.chatHistory = new String[20];
        //默认消息数量是0
    }

    /*
    获取名字
    @return 名字
    * */
    public String getFirstName() {
        return firstName;
    }

    /*
    获取姓氏
    @return 姓氏
    * */
    public String getLastName() {
        return lastName;
    }

    /*
     * 获取电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /*
    更新名字
    @return 新的名字
    * */
    public void updateFirstName(String newFirstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("firstName cannot be null or  empty");
        }
        this.firstName = newFirstName;
    }

    /*
更新姓氏
@return 新的形式
* */
    public void updateLastName(String newLastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or  empty");
        }
        this.lastName = newLastName;
    }

    /*
更新电话号码
@return 新的电话号码
* */
    public void updatePhoneNumber(String newPhoneNumber) {
        if (phoneNumber == null || phoneNumber.length() > 14 || phoneNumber.length() < 6) {
            throw new IllegalArgumentException("phoneNumber must be between and 14!");
        }
        this.phoneNumber = newPhoneNumber;
    }

    /*
     * 创建联系人副本
     * @return 返回当前联系人的副本
     * */
    public KasaburyContact copy() {
        //根据有参构造方法创建对象,复制用原来的名字、姓氏和电话号码
        KasaburyContact copyKasaburyContact = new KasaburyContact(this.firstName, this.lastName, this.phoneNumber);
        //复制聊天记录
        for (int i = 0; i < this.messageCount; i++) {
            copyKasaburyContact.chatHistory[i] = this.chatHistory[i];
        }
        //复制联系人数量
        copyKasaburyContact.messageCount = this.messageCount;
        return copyKasaburyContact;
    }

    /*
     * 添加消息：每个联系人最多可以包含20条消息
     * @param message 发送的消息内容*/
    public void addChatMessage(String message) {
        if (messageCount < 20) {
            // 聊天记录未满20条就直接存入
            //把发送的消息内容储存到聊天记录的数组中
            chatHistory[messageCount] = message;
            messageCount++;//消息数量+1
        } else {
            //超过20条消息就覆盖最旧的聊天信息
            for (int i = 0; i < 19; i++) {
                //后面的一条消息覆盖掉前面一条消息（不包含最后一条）
                chatHistory[i] = chatHistory[i + 1];
            }
            //覆盖掉最后一条消息
            chatHistory[19] = message;

        }
    }
    /*
    获取最后一条消息
    @return 最后一条消息，如果该联系人没有消息，该方法应该返回null*/

    public String getLastMessage() {
        //规避掉空聊天信息,该方法应该返回null
        if (messageCount == 0) {
            return null;
        }
        //最后一条信息从聊天记录的数组中获取
        return chatHistory[this.messageCount - 1];
    }

    /*
获取最旧一条消息
@return 最旧一条消息，如果该联系人没有消息，该方法应该返回null*/
    public String getOldestMessage() {
        //规避掉空聊天信息,该方法应该返回null
        if (messageCount == 0) {
            return null;
        }
        //最旧一条信息就是第一条消息
        return chatHistory[0];
    }

    /*清空聊天记录数组*/
    public void clearChatHistory() {
        //遍历聊天记录数组
        for (int i = 0; i < messageCount; i++) {
            chatHistory[i] = null;
        }
        //消息数量爷要归零
        this.messageCount = 0;
    }

    /*获取消息数量
     * @return*/
    public int getMessageCount() {
        return this.messageCount;
    }



    public boolean checkNameMatches(String name) {
        if (this.firstName.equalsIgnoreCase(name) || this.lastName.equalsIgnoreCase(name)) {
            return true;
        } else {
            return false;
        }
    }

}
