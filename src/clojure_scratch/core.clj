(ns clojure-scratch.core
  (:gen-class)
  (:require [clojure.spec.alpha :as s]))

(s/def :foo/bar
  (s/keys :req-un [:fizz/buzz]))

(s/valid? :foo/bar {:buzz nil})

(s/def :my-ns-where-strings-live/foo-bar
  (s/and (s/keys :req [:foo/bar])
         #(-> % :foo/bar string?)))

(s/def :my-ns-where-keywords-live/foo-bar
  (s/and (s/keys :req [:foo/bar])
         #(-> % :foo/bar keyword?)))

(s/def ::something-else :my-ns-where-strings-live/foo-bar)

(s/def ::a-something-else
  (s/keys :req-un [::something-else]))

(s/valid? ::a-something-else
          {:something-else {:foo/bar "string"}})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
