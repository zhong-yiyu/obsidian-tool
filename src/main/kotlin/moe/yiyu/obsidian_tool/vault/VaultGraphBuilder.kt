package moe.yiyu.obsidian_tool.vault

class VaultGraphBuilder {
    private lateinit var graph: VaultGraph
    constructor(choice: String) {
        when (choice) {
            "naive" -> graph = VaultGraphNaive()
            "high-performance" -> graph = VaultGraphHighPerformance()
        }
    }
}