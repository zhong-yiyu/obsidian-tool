package moe.yiyu.obsidian_tool.vault

class BackLink {
    var rawLink: String
    var link: String = ""
    var anchor: String? = null
    var alias: String? = null

    constructor(input: String) {
        this.rawLink = input
        if (this.rawLink.contains("#") and this.rawLink.contains("|")) {
            println(input)
            this.dualHandler()
        } else if (this.rawLink.contains("#") and !this.rawLink.contains("|")) {
            println("Anchor Handler")
            this.anchorHandler()
        } else if (this.rawLink.contains("|") and !this.rawLink.contains("#")) {
            this.aliasHandler()
        }
    }

    private fun anchorHandler() {
        this.anchor = this.rawLink.split("#")[1]
        this.link = this.rawLink.split("#")[0]
    }

    private fun aliasHandler() {
        this.link = this.rawLink.split("|")[0]
        this.alias = this.rawLink.split("|")[1]
    }

    private fun dualHandler() {
        this.link = this.rawLink.split("|")[0]
        val tmp = this.rawLink.split("|")[1]
        this.alias = tmp.split("#")[0]
        this.anchor = tmp.split("#")[1]
    }
}