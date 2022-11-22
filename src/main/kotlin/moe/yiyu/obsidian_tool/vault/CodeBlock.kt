package moe.yiyu.obsidian_tool.vault

class CodeBlock {
    var code:String
    var language:String
    var startLine:Int
    var endLine:Int
    constructor(code:String, language:String, startLine:Int, endLine:Int){
        this.code = code
        this.language = language
        this.startLine = startLine
        this.endLine = endLine
    }

    override fun toString(): String {
        return "CodeBlock(code='$code', language='$language', startLine=$startLine, endLine=$endLine)"
    }
}