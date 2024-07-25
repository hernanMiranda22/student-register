package com.example.sistemaalumnosv2.common

import com.example.sistemaalumnosv2.menu_screen.data.model.TermData
import com.example.sistemaalumnosv2.menu_screen.ui.model.TermDataUi

fun TermData.toTermUI() : TermDataUi {
    return TermDataUi(
        firstTerm = this.firstTerm,
        secondTerm = this.secondTerm,
        thirdTerm = this.thirdTerm
    )
}