(ns clojure-scratch.clm.util
  (:require [portal.api :as p]))

(defn portal-submit
  [v]
  (p/submit v)
  v)
