(ns clojure-scratch.clm.db
  (:require [datomic.client.api :as d]
            [clojure.data :as data]
            [db.conn-v2 :as conn]
            [clojure.set :as set]
            [portal.api :as p]
            [commons.config :as config]
            [clojure.string :as str]))

;; dev local db
(comment
  (do (def dbc (d/client (assoc (config/db-config) :proxy-port 8182)))
      (def dbn "mstokley")
      (def conn (conn/init-conn dbc dbn))))

;; QA
;; datomic-access -p mt2-qa --port 8184 client qa
(comment
  (do
    (def config {:server-type   :cloud
                 :region        "us-east-2"
                 :system        "qa"
                 :endpoint      "http://entry.qa.us-east-2.datomic.net:8182"
                 :creds-profile "mt2-qa"
                 :proxy-port    8184})
    (def qa-client (d/client config))
    (def qa-dbn "qa")
    (def qa-conn (d/connect qa-client {:db-name qa-dbn})))

  (d/list-databases qa-client {}))

;; QA2
;; datomic-access -p mt2-qa2 --port 8186 client qa2
(comment

  (do
    (def config {:server-type   :cloud,
                 :region        "us-east-2",
                 :system        "qa2",
                 :endpoint      "http://entry.qa2.us-east-2.datomic.net:8182"
                 :creds-profile "mt2-qa2"
                 :proxy-port    8186})
    (def client (d/client config))
    (def qa2-dbn "qa2")
    (def qa2-conn (d/connect client {:db-name qa2-dbn}))
    (def qa2-db (d/db qa2-conn)))

  (d/list-databases client {}))

;; PROD
;; most databases are in PROD, even -baseline and -training

;; datomic-access -p mt2-prod --port 8187 client meditract

(comment
  (def prod-config {:server-type   :cloud
                    :region        "us-east-2"
                    :system        "meditract"
                    :endpoint      "http://entry.tempquerygroup.us-east-2.datomic.net:8182"
                    :creds-profile "mt2-prod"
                    :proxy-port    8187})

  (def prod-client (d/client prod-config)))

