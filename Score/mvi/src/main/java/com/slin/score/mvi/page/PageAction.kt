package com.slin.score.mvi.page

import com.slin.score.mvi.Action

/**
 * author: slin
 * date: 2021/11/15
 * description:
 *
 */
sealed class PageAction : Action {

    object Refresh : PageAction()

    object LoadMore : PageAction()

}