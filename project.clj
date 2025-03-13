(defproject reporte "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [compojure "1.7.1"]
                 [lib-noir "0.9.9"]
                 [ring/ring-core "1.12.2"]
                 [hiccup "1.0.5"]
                 [pdfkit-clj "0.1.7"]]
  :main ^:skip-aot reporte.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
