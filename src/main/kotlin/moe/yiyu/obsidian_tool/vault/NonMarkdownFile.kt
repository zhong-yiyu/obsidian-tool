package moe.yiyu.obsidian_tool.vault

import java.io.File

class NonMarkdownFile(vaultFile: File) :VaultFile{
    var file: File = vaultFile
    var name:String
    override fun getFileName(): String {
        return "$name (Non-Markdown)"
    }

    init {
        this.name = vaultFile.name
    }
}