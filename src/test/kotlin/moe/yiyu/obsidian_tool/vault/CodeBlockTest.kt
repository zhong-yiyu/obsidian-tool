package moe.yiyu.obsidian_tool.vault

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class CodeBlockTest{
        @Test
        fun testCodeBlock(){
            val codeBlock = CodeBlock("```python\nprint(\"Hello World\")\n```")
            assertEquals("python",codeBlock.language)
            assertEquals("print(\"Hello World\")",codeBlock.code)
        }
    @Test
    fun testCodeBlockLong(){
        val codeBlock = CodeBlock("```java\npublic class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n    }\n}\n```")
        assertEquals("java",codeBlock.language)
        assertEquals("public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n    }\n}",codeBlock.code)
    }

    @Test
    fun testIfCodeBlockExists(){
        val documentA = Document(MarkdownFile(File("src/test/resources/A.md")))
        assertEquals(false,documentA.hasCodeBlocks())
        val documentB = Document(MarkdownFile(File("src/test/resources/B.md")))
        assertEquals(true,documentB.hasCodeBlocks())
        val documentC = Document(MarkdownFile(File("src/test/resources/C.md")))
        assertEquals(false,documentC.hasCodeBlocks())
    }

    @Test
    fun testCodeBlockList(){
        val documentB = Document(MarkdownFile(File("src/test/resources/B.md")))
        assertEquals(1,documentB.findAllCodeBlocks().size)
        val documentD = Document(MarkdownFile(File("src/test/resources/D.md")))
        assertEquals(3,documentD.findAllCodeBlocks().size)
    }
}