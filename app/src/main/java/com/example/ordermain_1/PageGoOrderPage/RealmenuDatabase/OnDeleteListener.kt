package com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase

import com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase.DrinkmenuEntity
import com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase.SidemenuEntity

interface OnDeleteListener {
    fun onrealmenuDeleteListner(realmenuEntity: RealmenuEntity)
    fun onsidemenuDeleteListener(sidemenuEntity: SidemenuEntity)
    fun ondrinkmenuDeleteListener(drinkmenuEntity: DrinkmenuEntity)
}