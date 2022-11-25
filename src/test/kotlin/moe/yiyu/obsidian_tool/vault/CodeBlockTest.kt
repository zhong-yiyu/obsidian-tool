package moe.yiyu.obsidian_tool.vault

import org.junit.Assert.*
import org.junit.Test

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
}