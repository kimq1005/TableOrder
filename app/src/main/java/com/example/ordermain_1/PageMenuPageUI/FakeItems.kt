package com.example.ordermain_1.PageMenuPageUI

import com.example.ordermain_1.PageComOrderPage.ComOrderItem
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuEntity
import com.example.ordermain_1.R


val fakeBannerItemList =  listOf(
    BannerItem(R.drawable.food3),
    BannerItem(R.drawable.food2),
    BannerItem(R.drawable.food1),
    BannerItem(R.drawable.food1),
    BannerItem(R.drawable.food2)
)



val fakeMenuinformation = listOf(
    Menu_informationItem(
            "명문초밥 수원점",
            "최근리뷰2000||진짜리뷰3000",
        "최소주문금액: 6,500원",
        "결제방법: 카드결제, 현금결제",
        "조리시간: 50분 소요 예상")
)

val fakeRealMenu = listOf(
    RealMenuItem(
        R.drawable.food3,
        "왕초밥",
        "왕초밥은 리얼 개맛있고요 어쩌고 저쩌고아잉 왕진짜 후회안하는 개쩌는 맛입니다.우왕키윽",
        16000),


    RealMenuItem(
        R.drawable.food2,
    "참치초밥",
    "할 메뉴없어서 그냥 갖다붙인 초밥",
    12000),

        RealMenuItem(
            R.drawable.food1,
                "참치초밥",
                "할 메뉴없어서 그냥 갖다붙인 초밥",
                30000),

        RealMenuItem(
            R.drawable.food2,
                "참치초밥",
                "할 메뉴없어서 그냥 갖다붙인 초밥",
                2000),
        RealMenuItem(
            R.drawable.food2,
                "참치초밥",
                "할 메뉴없어서 그냥 갖다붙인 초밥",
                3000),
        RealMenuItem(
            R.drawable.food2,
                "참치초밥",
                "할 메뉴없어서 그냥 갖다붙인 초밥",
                5000),










)

val fakeSideMenu = listOf(
        SidemenuItem(
            R.drawable.food1,
                "셀러드",
                "요러요한 셀러드입니다",
                3000
        ),

        SidemenuItem(
            R.drawable.food1,
                "셀러드",
                "요러요한 셀러드입니다",
                3400
        ),

        SidemenuItem(
            R.drawable.food1,
                "셀러드",
                "요러요한 셀러드입니다",
                5500
        ),

        SidemenuItem(
            R.drawable.food1,
                "셀러드",
                "요러요한 셀러드입니다",
                6700
        ),

        SidemenuItem(
            R.drawable.food1,
                "셀러드",
                "요러요한 셀러드입니다",
                8800
        ),

        SidemenuItem(
            R.drawable.food1,
                "셀러드",
                "요러요한 셀러드입니다",
                102990
        )


)

val fakeDrinkMenu=listOf(
        DrinkmenuItem(
            R.drawable.food2,
        "소주",
        "이건 소주다",
        4000),

        DrinkmenuItem(
            R.drawable.food2,
                "소주",
                "이건 소주다",
                3500),

        DrinkmenuItem(
            R.drawable.food2,
                "소주",
                "이건 소주다",
                4500),

        DrinkmenuItem(
            R.drawable.food2,
                "소주",
                "이건 소주다",
                6300),

        DrinkmenuItem(
            R.drawable.food2,
                "소주",
                "이건 소주다",
                7200),

        DrinkmenuItem(
            R.drawable.food2,
                "소주",
                "이건 소주다",
                4000),
)

val fakeComOrder=listOf(
    RealmenuEntity(1,"음식이름이다","29999")
)

