package foo

native("jQuery")
val jq: dynamic = noImpl

val COMMITS_PER_PAGE = 10

val GET_KOTLIN_COMMITS = "https://api.github.com/repos/JetBrains/kotlin/commits?sha=master&per_page=$COMMITS_PER_PAGE"
val GET_KOTLIN_WEB_DEMO_COMMITS = "https://api.github.com/repos/JetBrains/kotlin-web-demo/commits?sha=master&per_page=$COMMITS_PER_PAGE"

[suppress("NOTHING_TO_INLINE")]
inline fun dynamic.asArray(): Array<dynamic> = this

fun main(args: Array<String>) {
    jq { // <--- dynamic call too
//        jq.getJSON(GET_KOTLIN_COMMITS) { commits ->
//            val commitsDiv = jq("#kotlin-commits")
//
//            for (commit in commits.asArray()) {
//                commitsDiv.append("""
//                    <div>
//                        <a href=${commit.html_url}>${commit.sha.substring(0, 6)}</a>
//                        <span>${commit.commit.message}</span>
//                    </div>""")
//            }
//        }
//
        jq.getJSON(GET_KOTLIN_COMMITS) { commits ->
            val commitsDiv = jq("#kotlin-commits")

            for (commit in commits) {
                commitsDiv.append("""
                    <div>
                        <a href=${commit.html_url}>${commit.sha.substring(0, 6)}</a>
                        <span>${commit.commit.message}</span>
                    </div>""")
            }
        }

        jq.getJSON(GET_KOTLIN_WEB_DEMO_COMMITS) { commits ->
            val commitsDiv = jq("#kotlin-web-demo-commits")


//            for (i in 0..(commits.length: Int)) {
//                val commit = commits[i]
//
//                commitsDiv.append("""
//                    <div>
//                        <a href=${commit.html_url}>${commit.sha.substring(0, 6)}</a>
//                        <span>${commit.commit.message}</span>
//                    </div>""")
//            }

            commits.forEach { commit ->
                commitsDiv.append("""
                    <div>
                        <a href=${commit.html_url}>${commit.sha.substring(0, 6)}</a>
                        <span>${commit.commit.message}</span>
                    </div>""")
            }
        }
    }
}