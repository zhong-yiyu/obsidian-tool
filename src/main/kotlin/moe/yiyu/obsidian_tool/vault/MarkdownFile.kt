package moe.yiyu.obsidian_tool.vault

import java.io.File

class MarkdownFile(vaultFile: File) :VaultFile {
    var file: File = vaultFile
    var name:String
    override fun getFileName(): String {
        return name
    }

    init {
        this.name = vaultFile.name
    }

    fun toDocument(): Document {
        return Document(this)
    }

}