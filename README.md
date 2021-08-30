# xsg-aggregation-printer-cloud
聚合打印

能力：
  1.打印模版动态可配置，模版可定制，支持多种输出方式(含：图片，PDF，指令（tspl, cpcl, esc[待支持]几大主流打印指令），未来将适配更多的打印机)
  2.提供各种打印模版，提供模版配置社区，根据不同场景定制打印模版
  3.可以根据自身需求做自定义模版字段配置
  
部分效果展示：

1.圖片/PDF：

![db82f7fa-5791-48a0-bf2a-6047edbeefa0](https://user-images.githubusercontent.com/35826080/131413117-b7d73c87-efe3-42be-bd5a-dc0d0788ca94.png)

2.TSPL指令：

      SIZE 70 mm,100 mm
      GAP 2 mm,0 mm
      CLS
      BAR 0,80,560,1
      TEXT 8,19,"TSS32.BF2",0,1,1,"#38"
      TEXT 9,20,"TSS32.BF2",0,1,1,"#38"
      TEXT 264,15,"TSS24.BF2",0,1,1,"小高快送"
      TEXT 265,16,"TSS24.BF2",0,1,1,"小高快送"
      TEXT 264,45,"TSS24.BF2",0,1,1,"客服:15613874848"
      BAR 0,144,560,1
      TEXT 16,96,"TSS32.BF2",0,1,1,"罗湖"
      TEXT 17,97,"TSS32.BF2",0,1,1,"罗湖"
      TEXT 92,104,"TSS24.BF2",0,1,1,"坂田街道"
      BAR 0,288,560,1
      TEXT 5,154,"TSS32.BF2",0,1,1,"收"
      TEXT 6,155,"TSS32.BF2",0,1,1,"收"
      TEXT 81,149,"TSS32.BF2",0,1,1,"雪人汽修中心"
      TEXT 82,150,"TSS32.BF2",0,1,1,"雪人汽修中心"
      TEXT 80,190,"TSS24.BF2",0,1,1,"18129858526"
      TEXT 81,191,"TSS24.BF2",0,1,1,"18129858526"
      TEXT 81,217,"TSS24.BF2",0,1,1,"江西省九江市都昌县都昌一中 老校区 雪人"
      TEXT 81,241,"TSS24.BF2",0,1,1,"汽车维修厂"
      BAR 0,408,560,1
      TEXT 5,298,"TSS32.BF2",0,1,1,"寄"
      TEXT 6,299,"TSS32.BF2",0,1,1,"寄"
      TEXT 81,293,"TSS24.BF2",0,1,1,"大石头汽配中心"
      TEXT 82,294,"TSS24.BF2",0,1,1,"大石头汽配中心"
      TEXT 81,322,"TSS24.BF2",0,1,1,"18129872727"
      TEXT 81,349,"TSS24.BF2",0,1,1,"江西省九江市都昌县都昌一中老校区一栋10"
      TEXT 81,373,"TSS24.BF2",0,1,1,"6大石头汽配"
      BAR 0,624,560,1
      BAR 0,520,120,1
      TEXT 30,424,"TSS32.BF2",0,1,1,"88"
      TEXT 31,425,"TSS32.BF2",0,1,1,"88"
      TEXT 15,472,"TSS24.BF2",0,1,1,"龙华龙华"
      TEXT 16,473,"TSS24.BF2",0,1,1,"龙华龙华"
      TEXT 25,536,"TSS32.BF2",0,1,1,"5件"
      TEXT 26,537,"TSS32.BF2",0,1,1,"5件"
      TEXT 25,584,"TSS24.BF2",0,1,1,"1超大333"
      TEXT 25,608,"TSS24.BF2",0,1,1,"大111中2"
      BAR 120,408,1,216
      QRCODE 180,422,L,7,A,0,"BC2012187480039"
      TEXT 152,592,"TSS24.BF2",0,1,1,"单号:BC2012187480039"
      BAR 400,408,1,216
      BAR 400,512,160,1
      TEXT 426,440,"TSS32.BF2",0,1,1,"有代收"
      TEXT 427,441,"TSS32.BF2",0,1,1,"有代收"
      TEXT 408,520,"TSS24.BF2",0,1,1,"收件签字"
      TEXT 440,600,"TSS24.BF2",0,1,1,"年  月  日"
      BAR 0,688,560,1
      TEXT 10,643,"TSS24.BF2",0,1,1,"备注: 这里有很多东西需要帮忙一起搬回家，然后放"
      TEXT 11,644,"TSS24.BF2",0,1,1,"备注: 这里有很多东西需要帮忙一起搬回家，然后放"
      TEXT 10,667,"TSS24.BF2",0,1,1,"在那个"
      TEXT 11,668,"TSS24.BF2",0,1,1,"在那个"
      BAR 0,736,560,1
      TEXT 10,700,"TSS24.BF2",0,1,1,"结算: 余额支付"
      TEXT 11,701,"TSS24.BF2",0,1,1,"结算: 余额支付"
      TEXT 10,743,"TSS24.BF2",0,1,1,"打印时间:2020-12-10 12:56:30"
      TEXT 380,743,"TSS24.BF2",0,1,1,"打印人:李信息"
      PRINT 1,1

3.cpcl指令：

      ! 0 200 200 800 1
      PAGE-WIDTH 560
      GAP-SENSE
      SET-TOF 0
      LINE 0 80 560 80 2
      SETBOLD 2
      SETMAG 2 2
      TEXT 55 0 8 19 #38
      SETMAG 0 0
      SETBOLD 0
      SETBOLD 2
      TEXT 11 0 264 15 小高快送
      SETBOLD 0
      TEXT 11 0 264 45 客服:15613874848
      LINE 0 144 560 144 2
      SETBOLD 2
      SETMAG 2 2
      TEXT 55 0 16 96 罗湖
      SETMAG 0 0
      SETBOLD 0
      TEXT 11 0 88 104 坂田街道
      LINE 0 288 560 288 2
      SETBOLD 2
      SETMAG 2 2
      TEXT 55 0 5 154 收
      SETMAG 0 0
      SETBOLD 0
      SETBOLD 2
      SETMAG 2 2
      TEXT 55 0 81 149 雪人汽修中心
      SETMAG 0 0
      SETBOLD 0
      SETBOLD 2
      TEXT 11 0 80 190 18129858526
      SETBOLD 0
      TEXT 11 0 81 217 江西省九江市都昌县都昌一中 老校区 雪人
      TEXT 11 0 81 241 汽车维修厂
      LINE 0 408 560 408 2
      SETBOLD 2
      SETMAG 2 2
      TEXT 55 0 5 298 寄
      SETMAG 0 0
      SETBOLD 0
      SETBOLD 2
      TEXT 11 0 81 293 大石头汽配中心
      SETBOLD 0
      TEXT 11 0 81 322 18129872727
      TEXT 11 0 81 349 江西省九江市都昌县都昌一中老校区一栋10
      TEXT 11 0 81 373 6大石头汽配
      LINE 0 624 560 624 2
      LINE 0 520 120 520 2
      SETBOLD 2
      SETMAG 2 2
      TEXT 55 0 30 424 88
      SETMAG 0 0
      SETBOLD 0
      SETBOLD 2
      TEXT 11 0 15 472 龙华龙华
      SETBOLD 0
      SETBOLD 2
      SETMAG 2 2
      TEXT 55 0 25 536 5件
      SETMAG 0 0
      SETBOLD 0
      TEXT 11 0 25 584 1超大333
      TEXT 11 0 25 608 大111中2
      LINE 120 408 120 624 2
      BARCODE QR 180 422 M 2 U 6
      MA,BC2012187480039
      ENDQR
      TEXT 11 0 152 592 单号:BC2012187480039
      LINE 400 408 400 624 2
      LINE 400 512 560 512 2
      SETBOLD 2
      SETMAG 2 2
      TEXT 55 0 426 440 有代收
      SETMAG 0 0
      SETBOLD 0
      TEXT 11 0 408 520 收件签字
      TEXT 11 0 440 600 年  月  日
      LINE 0 688 560 688 2
      SETBOLD 2
      TEXT 11 0 10 643 备注: 这里有很多东西需要帮忙一起搬回家，然后放
      TEXT 11 0 10 667 在那个
      SETBOLD 0
      LINE 0 736 560 736 2
      SETBOLD 2
      TEXT 11 0 10 700 结算: 余额支付
      SETBOLD 0
      TEXT 11 0 10 743 打印时间:2020-12-10 12:56:30
      TEXT 11 0 380 743 打印人:李信息
      FORM
      PRINT



完善中......

歡迎感興趣的朋友可以加入......
