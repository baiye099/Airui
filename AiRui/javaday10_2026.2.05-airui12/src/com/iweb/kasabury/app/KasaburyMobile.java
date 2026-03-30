package com.iweb.kasabury.app;

/**
 * Kasabury 手机操作系统的主类，管理所有手机功能
 * 包括：电池管理、网络管理、联系人管理、消息管理等功能(自己后期可以拓展TODO)
 */
/*
 * Kasabury 手机操作系统的主类，管理所有手机功能
 *包括：电池管理、网络管理、联系人管理、消息管理等功能（自己后期可以拓展 TODO） */

public class KasaburyMobile {
    private static final int MAX_CONTACT = 11;    //定义一个最大联系人常量
    private boolean isPhoneOn;//手机是否开启 true开机 false关机
    private int batteryLife;//电池电量[0,100]
    private boolean isConnectedNetwork; // 是否连接网络 true连接 false断开
    private int signalStrength; // 信号强度表示为0到5(包括0,5)
    private KasaburyContact[] kasaburyContacts; // 联系人数组，10加上所有者(拥有者)联系人=11
    private KasaburyContact ownerKasaburyContact; // 主机联系人(拥有者)
    private int contactCount; // 联系人数量
    //===================电池管理相关功能===================
    private boolean isPhone;//手机是否开启 true开机 false关机


    /*
    * 无参构造方法：初始化手机操作系统
    * 【机主联系方式】
    * 名: Kasabury
        姓: Incorporated
        电话号码: 18007623786
        默认消息:  "Kasabury: Thank you for choosing Kasabury products"
        * 【出场默认设置】
        * 手机状态: 关机
           电池寿命: 25
            网络连接: 未连接
            信号强度: 0*/
    public KasaburyMobile() {
        //【机主联系方式】
        this.ownerKasaburyContact = new KasaburyContact("Kasabury", "Incorporated", "18007623786");
        //【出场默认设置】
        this.isPhone = false;
        this.batteryLife = 25;
        this.isConnectedNetwork = false;
        this.signalStrength = 0;
        //初始化联系人数组
        this.kasaburyContacts = new KasaburyContact[MAX_CONTACT];
        //初始化联系人数量
        this.contactCount = 0;
        //初始化应该包含一条信息
        this.ownerKasaburyContact.addChatMessage("Kasabury: Thank you for choosing Kasabury products");
    }

    //===================电池管理相关功能===================
    /*检查手机是否开机*/
    public boolean isPhoneOn() {
        return isPhoneOn;
    }


    /*获取电池寿命
     *@ return 返回电池电量[0,100]*/

    public int getBatteryLife() {
        return batteryLife;
    }

    /*
     * 更换电池
     * 如果新电池是超出接受范围（n<0 or n>100），操作失败不能更新
     * @param n 新电池的电量取值范围，在[0,100]范围内
     * @return 电池正常更换就返回true，超出接受范围返回false
     * */
    public boolean changeBattery(int n) {
        if (n < 0 | n > 100) {

            return false;//超出接受范围返回false
        }
        this.batteryLife = n;//把最新的电池电量复制给电池电量的变量
        this.isPhone = false;
        return true;
    }

    /*充电
     *  充电手机（每次增加10，上限100），当电池充电超过100，充电量就变成100
     * 如果没有任何改变，则不会发生收费，该方法应该返回false
     * @return 正常充电返回true，否则返回false
     * */
    public boolean chargePhone() {
        //先把电池电量赋值给临时变量
        int tempBattery = this.batteryLife;
        //每次充电电量增加10
        this.batteryLife += 10;
        //当电池充电超过100时，充电量就变成100
        if (this.batteryLife > 100) {
            batteryLife = 100;
        }
        //如果没有任何改变，则不会发生收费，该方法应该返回false
        return tempBattery != this.batteryLife;
    }


    /*使用手机消耗电量
     * @param k 减少电池的电量*/
    public void usePhone(int k) {
        this.batteryLife -= k;
        if (this.batteryLife <= 0) {
            // 自动关机
            isPhoneOn = false;
            this.batteryLife = 0; // 电池耗尽
        }
    }

    /*开机
     * /**
     * 开机
     * 打开手机会使电池电量减少5，如果电池电量< 6，手机无法开机
     * @return 开机成功返回true，电池电量< 6，手机无法开机返回false
     */
    public boolean setPhoneOn() {
        if (this.isPhoneOn == false) {
            if (this.batteryLife < 6) {
//电池电量<6，手机无法开机
                return false;
            }
            //开机消耗电量5
            this.batteryLife -= 5;
            isPhoneOn = true;
            return true;
        } else {
            return false;
        }

    }
//===================网络管理相关功能===================

    /**
     * 检查网络连接：确保手机开机状态下
     *
     * @return 如果正常连接返回true，否则返回false
     */
    public boolean isConnectedNetwork() {
        if (this.isPhoneOn == true) {
            return this.isConnectedNetwork;
        }
        return false;
    }

    /**
     * 连接网络:确保在开机状态下
     * 1、如果需要，则连接到网络，否则什么也不做。
     * 2、如果信号强度当前设置为0，当连接到网络，设置信号强度为1。
     * 3、如果当前信号强度不是0则默认当前信号强度。
     * 4、如果网络需要连接，这个过程会使电池电量减少2。
     */
    public void connectNetwork() {
        if (this.isPhoneOn == true) {
            if (!isConnectedNetwork) {
                isConnectedNetwork = true;
                batteryLife -= 2;
                if (signalStrength == 0) {
                    signalStrength = 1;
                }
            }
        }
    }

    /**
     * 断开网络：确保手机开机状态下
     * 断开与网络的连接并将信号强度设为0
     */
    public void disconnectNetwork() {
        if (this.isPhoneOn == true) {
            this.signalStrength = 0; // 信号强度设为0
            this.isConnectedNetwork = false; // 断开网络
        }
    }

    /**
     * 获取信号强度
     *
     * @return 返回一个介于0和5之间的整数值[0, 5]
     */
    public int getsignalstrength() {
        return this.signalStrength;
    }

    /**
     * 设置信号强度：设置信号强度为n，其中n必须在[0,5]的范围内。
     * 1、如果n在[0,5]的范围内，方法将成功。
     * 2、如果手机没有连接到一个网络并且n>0，它将连接到一个网络和减少电池寿命2。
     * 3、如果已经连接到网络，则更新信号强度值。如果信号强度为0，则断开网络，而信号强度>0，则不会改变连接状态
     * 4、如果n超出了[0,5]的范围，或者手机关机，这个方法不会改变，并指定它没有成功更新。
     */
    public boolean setSignalStrength(int n) {
        //检查后记是否关机，如果是关机，后面就不执行了
        // TODO: 实现设置信号强度逻辑
        if(isPhoneOn){
            return false;
        }
        //检查要设置的信号强度是否在[0,5]范围内
        if (n<0||n>5){
            return false;
        }
// 如果手机没有连接网络并且n>0，则连接网络同时电池电量减2
        if (!isConnectedNetwork && n > 0) {
            isConnectedNetwork = true; // 连接网络
            batteryLife -= 2; // 电池电量减2
            signalStrength = n; // 设置当前信号强度
            return true; // 成功返回true
        }

// 如果已连接到网络
        if (isConnectedNetwork) {
            // 更新信号强度值
            signalStrength = n;
            // 如果信号强度为0，则断开网络
            if (signalStrength == 0) {
                isConnectedNetwork = false;
            }
            return true;
        }
        return false;
    }


    //===================联系人管理相关功能===================
    /*
    * 添加联系人
    *   1、给定一个KasaburyContact，操作系统应该将此联系人添加到联系人列表中。
         2、只有在有足够的空间时才能这样做。
         3、如果phone关闭，该方法不应该添加一个联系人，并返回添加联系人失败。
    * @param kasaburyContact你要添加的联系人
    * @return 如果添加联系人成功返回true,否则返回false
    **/
    public boolean addContact(KasaburyContact kasaburyContact) {
        //确保开机状态下,如果是关机,后面就不执行了
        if (!isPhoneOn) {
            return false;
        }
        //检查要移除的联系人是否为空，如果是空就不让移除
        if (kasaburyContact == null) {
            return false;
        }
        //只有在足够的空间才能这样做,从0开始
        if (contactCount >= MAX_CONTACT - 1) {
            return true;
        }
        //联系人数组+1
        kasaburyContacts[contactCount++] = kasaburyContact;
        return true;
    }
/*
*移除联系人
 1、给定一个KasaburyContact，操作系统应该从联系人列表中删除该联系人。
  2、如果找到并删除了联系人，则表示成功。否则失败。
  3、如果电话关闭，该方法不应该删除一个联系人，并返回删除联系人失败。
  4、无效的联系人(如null)将导致失败。
    * @param kasaburyContact你要移除的联系人
    * @return 如果添加联系人成功返回true,否则返回false
 */

    public boolean removeContact(KasaburyContact kasaburyContact) {
        //确保开机状态下,如果是关机,后面就不执行了
        if (!isPhoneOn) {
            return false;
        }
        //检查要移除的联系人是否为空，如果是空就不让移除
        if (kasaburyContact == null) {
            return false;
        }
        for (int i = 0; i < contactCount; i++) {
            if (kasaburyContacts[i].equals(kasaburyContact)) {
                //移除联系人,将后面的一个联系人往前移动一位
                for (int j = i; j < contactCount - 1; j++) {
                    //往前移动一位
                    kasaburyContacts[j] = kasaburyContacts[j + 1];
                }
                //当前一位置空
                kasaburyContacts[contactCount - 1] = null;
                contactCount--;
                return true;
            }
        }
        return false;
    }

    /*
    * 搜索联系人：
  1、用户可能希望找到存储在他们手机上的联系人。
  2、给定一个用户可以使用的输入名，操作系统应该检查联系人的姓或名是否与给定的输入匹配。
  3、如果字符串被匹配多次，该方法可以返回多个结果。
  4、如果电话是关闭的，该方法不应该继续执行，而是不返回任何条目。
  * @param name 你要输入的名字或者姓氏
  * @return匹配的联系人数组*/
    public KasaburyContact[] searchContact(String name) {
        //确保开机状态下,如果是关机,后面就不执行了
        if (!isPhoneOn) {
            //如果是关机,返回联系人空数组
            return new KasaburyContact[0];
        }
        //创建一个搜索的临时联系人数组
        KasaburyContact[] tempKasaburyContact = new KasaburyContact[MAX_CONTACT];
        int tempContactCount = 0;
        //使用的输入名,检查联系人的姓或名是否与给定的输入匹配
        if (this.ownerKasaburyContact.checkNameMatches(name)){
            //匹配,把联系人添加到临时数组中
            tempKasaburyContact[tempContactCount++] = this.ownerKasaburyContact;

        }
        //检查其他联系人
        for (int i = 0; i < contactCount; i++) {
            if (kasaburyContacts[i].checkNameMatches(name)) {
                //匹配,把联系人添加到临时数组中
                tempKasaburyContact[tempContactCount++] = kasaburyContacts[i];
            }
        }
        //创建一个结果数组
        KasaburyContact[] searchContactResult=new KasaburyContact[tempContactCount];
        //遍历临时联系人数组
        for (int i=0;i<tempContactCount;i++){
            searchContactResult[i]=tempKasaburyContact[i];
        }
        //返回出你搜索出联系人数组
        return tempKasaburyContact;
    }


//===================消息管理相关功能===================
    












}
