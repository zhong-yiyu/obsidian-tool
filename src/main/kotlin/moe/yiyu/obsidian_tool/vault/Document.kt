package moe.yiyu.obsidian_tool.vault

import java.util.logging.Logger

class Document(file: MarkdownFile) {
    var title: String
    var content: String
    var backLinks:List<BackLink>? = null
    var logger:Logger = Logger.getGlobal()

    init {
        this.title = file.name.substring(0, file.name.length - 3)
        this.content = file.file.readText()
        this.backLinks = this.parseBackLinks()
        logger.fine("Document has ${backLinks?.size} Links inside")
    }
    fun parseFrontMatter():String?{
//        First determine if there is a front matter
//        If there is a front matter, parse it
//        If there is no front matter, do nothing
        fun hasFrontMatter(): Boolean {
            return this.content.startsWith("---")
        }
        if (hasFrontMatter()) {
            return content.substring(0, content.indexOf("---", 3)).substring(5)
        }
        return null
    }
    private fun parseBackLinks(): List<BackLink>? {
        fun hasBackLinks(): Boolean {
            return this.content.contains("[[") and this.content.contains("]]")
        }
        if (hasBackLinks()) {
            return this.content.split("[[").drop(1).map { it.split("]]")[0] }.map { BackLink(it) }
        }
        return null
    }

    fun getHTTPLinks():List<String>{
//        Get all HTTP links in the content starting with "http" or "https"
        return this.content.split(" ").filter { it.startsWith("http") }
    }

    fun hasCodeBlocks(): Boolean {
        return this.content.contains("```")
    }
    fun getCodeBlockLanguage(): String {
            val codeBlock = this.content.split("```")[1]
//            println(codeBlock.split("\n")[0])
            return codeBlock.split("\n")[0]
    }
}