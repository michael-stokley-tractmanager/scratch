(ns clojure-scratch.clm.tickets.unknown)

(comment 
  @(def results
     (for [dbname all-production-databases
           :when (not (or
                       (str/includes? dbname "training")
                       (str/includes? dbname "2020")
                       (str/includes? dbname "2021")
                       (str/includes? dbname "baseline")))]

       (let [conn   (d/connect client {:db-name dbname})
             db     (d/db conn)]
         (build-tx-data db))))

  @(def beautmont-results
     (for [dbname all-production-databases
           :when (str/includes? dbname "beaumont")]

       (let [conn   (d/connect client {:db-name dbname})
             db     (d/db conn)]
         #_       (build-tx-data db)
         db)))

  (def beaumont-baseline
    (let [conn   (d/connect client {:db-name  "beaumont-terms-2021-03-24-13.04" #_ "beaumont-baseline"})
          db     (d/db conn)]
      (build-tx-data db)
      )
    )


  (->> results
       (remove empty?)
       (remove (fn [result] (some :parent result) ))))

