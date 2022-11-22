package moe.yiyu.obsidian_tool.vault
// 自己实现的方案，不采用第三方库，性能应该很低
// 但是可以实现自定义的查找方案
class VaultGraphNaive:VaultGraph {
    var nodes: List<VaultGraphNode> = mutableListOf()
    fun addNode(node: VaultGraphNode) {
        nodes = nodes.plus(node)
    }

    fun addNodes(nodes: List<VaultGraphNode>) {
        this.nodes = this.nodes.plus(nodes)
    }

    fun getNodeByTitle(title: String): VaultGraphNode? {
        return nodes.find { it.title == title }
    }

    fun addEdge(source: String, target: String) {
        val sourceNode = getNodeByTitle(source)
        val targetNode = getNodeByTitle(target)
        if (sourceNode != null && targetNode != null) {
            sourceNode.outNeighbors = sourceNode.outNeighbors.plus(targetNode)
            targetNode.inNeighbors = targetNode.inNeighbors.plus(sourceNode)
        }
    }

    fun addEdges(source: String, targets: List<String?>) {
        targets.forEach { it?.let { it1 -> addEdge(source, it1) } }
    }

    /**
     * 找出所有的弱连通分量
     */
    fun findAllWeakConnectedComponent(): List<List<VaultGraphNode>> {
        val connectedComponents = mutableListOf<List<VaultGraphNode>>()
        val visitedNodes = mutableListOf<VaultGraphNode>()
//        Find the weak connected component, don't need to find the strong connected component
        nodes.forEach { node ->
            if (!visitedNodes.contains(node)) {
                val connectedComponent = mutableListOf<VaultGraphNode>()
                connectedComponent.add(node)
                visitedNodes.add(node)
                val queue = mutableListOf<VaultGraphNode>()
                queue.add(node)
                while (queue.isNotEmpty()) {
                    val currentNode = queue.removeAt(0)
                    currentNode.outNeighbors.forEach { it ->
                        if (!visitedNodes.contains(it)) {
                            it?.let { it1 -> connectedComponent.add(it1) }
                            it?.let { it1 -> visitedNodes.add(it1) }
                            it?.let { it1 -> queue.add(it1) }
                        }
                    }
                    currentNode.inNeighbors.forEach { it ->
                        if (!visitedNodes.contains(it)) {
                            it?.let { it1 -> connectedComponent.add(it1) }
                            it?.let { it1 -> visitedNodes.add(it1) }
                            it?.let { it1 -> queue.add(it1) }
                        }
                    }
                }
                connectedComponents.add(connectedComponent)
            }
        }
        return connectedComponents
    }

    /**
     * 找出所有的强连通分量
     */
    fun findAllStrongConnectedComponent(): List<List<VaultGraphNode>> {
        val connectedComponents = mutableListOf<List<VaultGraphNode>>()
        val visitedNodes = mutableListOf<VaultGraphNode>()
        nodes.forEach { node ->
            if (!visitedNodes.contains(node)) {
                val connectedComponent = mutableListOf<VaultGraphNode>()
                connectedComponent.add(node)
                visitedNodes.add(node)
                val queue = mutableListOf<VaultGraphNode>()
                queue.add(node)
                while (queue.isNotEmpty()) {
                    val currentNode = queue.removeAt(0)
                    currentNode.outNeighbors.forEach { neighbor ->
                        if (!visitedNodes.contains(neighbor)) {
                            neighbor?.let { connectedComponent.add(it) }
                            neighbor?.let { visitedNodes.add(it) }
                            neighbor?.let { queue.add(it) }
                        }
                    }
                }
                connectedComponents.add(connectedComponent)
            }

        }
        return connectedComponents
    }

    /**
     * 找出所有的入度为0的节点，获得的就是孤立节点
     */
    fun findOrphanNodes(): List<VaultGraphNode> {
        return nodes.filter { it.inNeighbors.isEmpty() }
    }

    fun findRootNodes(): List<VaultGraphNode> {
        return nodes.filter { it.inNeighbors.isEmpty() }
    }

    fun findLeafNodes(): List<VaultGraphNode> {
        return nodes.filter { it.outNeighbors.isEmpty() }
    }

    fun findLongestPath():List<VaultGraphNode>{
        val longestPath = mutableListOf<VaultGraphNode>()
        val visitedNodes = mutableListOf<VaultGraphNode>()
        nodes.forEach { node ->
            if (!visitedNodes.contains(node)) {
                val path = mutableListOf<VaultGraphNode>()
                path.add(node)
                visitedNodes.add(node)
                val queue = mutableListOf<VaultGraphNode>()
                queue.add(node)
                while (queue.isNotEmpty()) {
                    val currentNode = queue.removeAt(0)
                    currentNode.outNeighbors.forEach { neighbor ->
                        if (!visitedNodes.contains(neighbor)) {
                            neighbor?.let { path.add(it) }
                            neighbor?.let { visitedNodes.add(it) }
                            neighbor?.let { queue.add(it) }
                        }
                    }
                }
                if (path.size > longestPath.size) {
                    longestPath.clear()
                    longestPath.addAll(path)
                }
            }
        }
        return longestPath
    }
}