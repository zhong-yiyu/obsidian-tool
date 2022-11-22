package moe.yiyu.obsidian_tool.vault

class VaultGraphNode {
    var title:String
    var outlinks:List<String?> = mutableListOf()
    var inlinks:List<String?> = mutableListOf()
    var outNeighbors:List<VaultGraphNode?> = mutableListOf()
    var inNeighbors:List<VaultGraphNode?> = mutableListOf()
    constructor(title:String, outlinks:List<String?>, inlinks:List<String?>){
        this.title = title
        this.outlinks = outlinks
        this.inlinks = inlinks
    }

    override fun toString(): String {
        return "VaultGraphNode(title='$title', outlinks=$outlinks, inlinks=$inlinks, outNeighbors=$outNeighbors, inNeighbors=$inNeighbors)"
    }
}