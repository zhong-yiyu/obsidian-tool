package moe.yiyu.obsidian_tool.vault

// 需要完善，最好引入第三方的专门处理YAML的库

class FrontMatter {
    var title:String? = null
    var aliases:List<String>? = null
    constructor(frontMatter: String) {
        val frontMatterMap = parseFrontMatter(frontMatter)
        this.title = frontMatterMap["title"] as String?
        this.aliases = frontMatterMap["aliases"] as List<String>?
    }
    fun parseFrontMatter(input:String):MutableMap<String, String>{
        var map:MutableMap<String, String> = mutableMapOf()
        val frontMatterLines = input.split("\n")
        frontMatterLines.forEach {
//            println(frontMatterLines)
            val key = it.substring(0, it.indexOf(":"))
            val value = it.substring(it.indexOf(":") + 1)
            println(value)
            map[key] = value
        }
        return map
    }
}