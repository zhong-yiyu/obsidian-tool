package moe.yiyu.obsidian_tool.vault

class CodeBlock//        输入的是整个代码块，包含了```和语言标识
//        remove the code's beginning empty line and ending empty line
(raw_input: String) {
    var code:String
    var language:String

    init {
        this.language = raw_input.split("\n")[0].replace("```","").trim()
        val codeTmp = raw_input.replace("```$language","").replace("```","")
        this.code = codeTmp.split("\n").drop(1).dropLast(1).joinToString("\n")
    }

    override fun toString(): String {
        return "CodeBlock(code='$code', language='$language')"
    }

}