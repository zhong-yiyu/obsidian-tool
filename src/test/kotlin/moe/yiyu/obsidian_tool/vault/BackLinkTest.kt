package moe.yiyu.obsidian_tool.vault

import org.junit.Test

import org.junit.Assert.*

class BackLinkTest {

    @Test
    fun anchorHandler() {
        val backLink = BackLink("test#anchor")
        assertEquals("test", backLink.link)
        assertEquals("anchor", backLink.anchor)
    }

    @Test
    fun getBackLink(){
        var link = "[[test]]"
        var tmp = link.split("[[").drop(1).map { it.split("]]")[0] }
        assertEquals("test", tmp[0])
    }

    @Test
    fun aliasHandlerTest(){
        val backLink = BackLink("test|alias")
//        assertEquals("test", backLink.link)
        assertEquals("alias", backLink.alias)
    }

    @Test
    fun dualHandlerTest(){
        val backLink = BackLink("test|alias#anchor")
        assertEquals("test", backLink.link)
        assertEquals("alias", backLink.alias)
        assertEquals("anchor", backLink.anchor)
    }
}