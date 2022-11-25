package moe.yiyu.obsidian_tool.vault

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class VaultGraphNaiveTest{
    @Test
    fun testAddEdge(){
        val graph = VaultGraphNaive()
        val documentA = Document(MarkdownFile(File("src/test/resources/A.md")))
        val documentB = Document(MarkdownFile(File("src/test/resources/B.md")))
        val documentC = Document(MarkdownFile(File("src/test/resources/C.md")))
    }
}