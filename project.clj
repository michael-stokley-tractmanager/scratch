(defproject clojure-scratch "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [com.datomic/client-cloud "0.8.102" :exclusions [com.amazonaws/aws-java-sdk-s3]]
                 [org.clojure/clojure "1.10.0"]
                 [tractsoft/commons "0.5.299"]
                 [tractsoft/shared-db "0.29.104" :exclusions [tractsoft/commons]]
                 [djblue/portal "0.11.1"]]
  :plugins [[s3-wagon-private "1.3.4"]
            [lein-parent "0.3.7"]]
  :parent-project {:coords [tractsoft/commons "0.5.209"]
                   :inherit [:managed-dependencies]}
  :main ^:skip-aot clojure-scratch.core
  :target-path "target/%s"
  :repositories [["releases" {:url           "s3p://mt2-maven/releases/"
                              :no-auth       true
                              :sign-releases false}]]
  :profiles {:uberjar {:aot :all}})
