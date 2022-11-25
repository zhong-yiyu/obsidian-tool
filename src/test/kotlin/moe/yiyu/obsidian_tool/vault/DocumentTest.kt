package moe.yiyu.obsidian_tool.vault

import org.junit.Assert.*
import org.junit.Test
import java.io.File

var testFileA = MarkdownFile(File("src/test/resources/A.md"))
val documentA = Document(testFileA)
val testFileB = MarkdownFile(File("src/test/resources/B.md"))
val documentB = Document(testFileB)
val testFileC = MarkdownFile(File("src/test/resources/C.md"))
val documentC = Document(testFileC)

class DocumentTest{
    @Test
    fun testBackLink(){
        assertEquals(2, documentA.backLinks?.size)
//        这里的长度不是0，而是null
        assertEquals(null, documentB.backLinks?.size)
        assertEquals(1, documentC.backLinks?.size)
    }
}