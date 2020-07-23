// pages/productDetail/productDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    productID: "",

    detailList: [{
        detailId: "001",
        productImg: "http://img5.imgtn.bdimg.com/it/u=360930211,517368292&fm=26&gp=0.jpg",
        info: "金钱酥-广州情-酥饼礼盒",
        price: "29.00",
        comments: [{
            user: "小明",
            time: "2019-10-10",
            form: "本地宝",
            usercomment: "#包装结实，日期新，味道好，价格实惠##包装结实，日期新，味道好，价格实惠#"
          },
          {
            user: "23wxhas88",
            time: "2018-10-10",
            form: "本地宝",
            usercomment: "太好吃了，脆脆香香的，会继续回购。太好吃了，脆脆香香的，会继续回购。太好吃了，脆脆香香的，会继续回购。"
          },
          {
            user: "wcm888888",
            time: "2018-10-10",
            form: "本地宝",
            usercomment: "送礼好品，爸爸妈妈都喜欢吃，下次再来买。"
          }
        ]
      },
      {
        detailId: "002",
        productImg: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571168373643&di=d3f3d556c170c21891601a0caaa2b2c7&imgtype=0&src=http%3A%2F%2Fimg5.21food.cn%2Fimg%2Falbum%2F2017%2F9%2F21%2Ffood13548241428042Pe.jpg",
        info: "广式腊肠广东皇上皇特产添福400g香肠腊肉广州甜味送礼腊味煲仔饭",
        price: "24.00",
        comments: [{
            user: "小明",
            time: "2019-10-10",
            form: "本地宝",
            usercomment: "#八错不错，I like#"
          },
          {
            user: "23wxhas88",
            time: "2018-10-10",
            form: "本地宝",
            usercomment: "太好吃了，脆脆香香的，会继续回购。太好吃了，脆脆香香的，会继续回购。太好吃了，脆脆香香的，会继续回购。"
          }
        ]
      },
      {
        detailId: "003",
        productImg: "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1770282035,2654745903&fm=15&gp=0.jpg",
        info: "肉松酥-广州特产手信礼盒",
        price: "29.00",
        comments: [{
            user: "小明",
            time: "2019-10-10",
            form: "本地宝",
            usercomment: "#包装结实，日期新，味道好，价格实惠##包装结实，日期新，味道好，价格实惠#"
          },
          {
            user: "23wxhas88",
            time: "2018-10-10",
            form: "本地宝",
            usercomment: "太好吃了，脆脆香香的，会继续回购。太好吃了，脆脆香香的，会继续回购。太好吃了，脆脆香香的，会继续回购。"
          }
        ]
      },
      {
        detailId: "004",
        productImg: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571168646810&di=ead4c51ee7404da18f5c45d6d34bb02d&imgtype=0&src=http%3A%2F%2Fwww.xplian.net%2FtuxpJDA0JDI0L1RCMXFJRFlScCQ2YUphRiQ1JDM.jpg",
        info: "陶陶居",
        price: "29.00",
        comments: [{
            user: "小明",
            time: "2019-10-10",
            form: "本地宝",
            usercomment: "#包装结实，日期新，味道好，价格实惠##包装结实，日期新，味道好，价格实惠#"
          },
          {
            user: "23wxhas88",
            time: "2018-10-10",
            form: "本地宝",
            usercomment: "太好吃了，脆脆香香的，会继续回购。太好吃了，脆脆香香的，会继续回购。太好吃了，脆脆香香的，会继续回购。"
          }
        ]
      }
    ],

    detailObj: {},

    num: 1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.setData({
      productID: options.id
    });

    var arr = this.data.detailList;

    arr.forEach((v, i) => {

      if (v.detailId === options.id) {
        this.setData({
          detailObj: v
        });
        return;
      }

    })
    // 设置动态导航栏
    wx.setNavigationBarTitle({
      title: options.title
    })
  },

  // 添加到购物车的处理
  handleAddCart() {

    var obj = {
      img: this.data.detailObj.productImg,
      price: this.data.detailObj.price,
      info: this.data.detailObj.info,
      num: this.data.num,
      detailId: this.data.detailObj.detailId,
      singleChecked: false
    }
    // 先从本地获取商品数据
    wx.getStorage({
      key: 'cartList',
      success: (res) => {
        // console.log(res)
        var arr = res.data;
        var flag = true;
        arr.forEach((v, i) => {

          if (v.detailId == obj.detailId) {
            // bug 只能加 不能减
            obj.num += v.num;

            arr[i].num = obj.num;

            flag = false;
          }
        })

        if (flag) {

          arr.push(obj);
        }

        wx.setStorage({
          key: 'cartList',
          data: arr,
          success: () => {
            wx.showToast({
              title: '您已添加购物车',
              icon: 'none'
            })
          }
        })
      },
      fail: (err) => {
        console.log(err)

        if (err.errMsg == "getStorage:fail data not found") {
          wx.setStorage({
            key: 'cartList',
            data: [obj],
            success:()=>{
              wx.showToast({
                title: '您已添加购物车',
                icon:'none'
              })
            }
          })
        }

      }
    })


  },

  // 处理输入框内容的函数
  handleNum(e) {
    this.setData({
      num: parseInt(e.detail.value)
    })

  },
  // 加
  handleAdd(e) {
    var num = e.currentTarget.dataset.num;
    //console.log(num)
    num++;
    this.setData({
      num,
    })
  },
  // 减
  handleSub(e) {
    var num = e.currentTarget.dataset.num;
    num--;
    this.setData({
      num
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})