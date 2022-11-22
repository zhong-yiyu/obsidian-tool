package moe.yiyu.obsidian_tool.vault

class VaultGraphBuilder {
    private var graph:VaultGraph = VaultGraphHighPerformance()
    constructor(choice: String) {
        when (choice) {
            "naive" -> graph = VaultGraphNaive()
            "high-performance" -> graph = VaultGraphHighPerformance()
        }
    }
}