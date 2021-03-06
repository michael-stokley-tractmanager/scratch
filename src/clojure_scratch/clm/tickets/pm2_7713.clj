(ns clojure-scratch.clm.tickets.pm2-7713
  (:require [datomic.client.api :as d]
            [clojure.data :as data]
            [clojure.walk :as w]
            [clojure-scratch.clm.db :as db-util]
            [db.conn-v2 :as conn]
            [portal.api :as p]
            [commons.config :as config]))

(comment
  (p/open)

  )

(comment

  (do (def pm2-7713-dbc (d/client (assoc (config/db-config) :proxy-port 8182)))
      (def pm2-7713-dbn "mstokley-pm2-7713")
      (def pm2-7713-conn (conn/init-conn pm2-7713-dbc pm2-7713-dbn)))

  (do (def pm2-7713-2-dbc (d/client (assoc (config/db-config) :proxy-port 8182)))
      (def pm2-7713-2-dbn "mstokley-pm2-7713-2")
      (def pm2-7713-2-conn (conn/init-conn pm2-7713-2-dbc pm2-7713-2-dbn))))

(comment
  (p/open)

  (let [original-workflow-id          #uuid "b2bf427e-321b-4fd2-b4f9-8f3742a66eb4"
        post-clone-workflow-id        #uuid "8af02ea6-8fa9-48eb-8cec-92ea0f24febc"
        original-workflow-in-original (d/pull (d/db conn)
                                              '[*]
                                              [:workflow/id original-workflow-id])
        original-workflow-in-clone    (d/pull (d/db pm2-7713-conn)
                                              '[*]
                                              [:workflow/id original-workflow-id])
        post-clone-workflow           (d/pull (d/db pm2-7713-conn)
                                              '[*]
                                              [:workflow/id post-clone-workflow-id])
        original-first-section-in-original-db (d/pull (d/db conn) '[*] 87960930225550)
        original-first-section-in-clone-db    (d/pull (d/db pm2-7713-conn) '[*] 74766790692228)
        post-clone-first-section              (d/pull (d/db pm2-7713-conn) '[*] 74766790692253)

        original-form-template (d/pull (d/db conn) '[*]
                                       [:workflow.form/id #uuid "83752abd-2d20-471b-802b-729bb2052788"])
        cloned-form-template (d/pull (d/db pm2-7713-conn) '[*]
                                     [:workflow.form/id #uuid "83752abd-2d20-471b-802b-729bb2052788"])
        ]

    (-> cloned-form-template
        portal-submit)

    (data/diff original-form-template cloned-form-template))
  )

(comment
  (let [form-id [:workflow.form/id #uuid "d0d3b2c4-c69c-43ff-bc3b-b4eb50ed264e"]
        pattern '[* {:workflow.form/sectionmaps
                     [* {:form.sectionmap/section [*]}]}]
        original-form (d/pull (d/db conn)
                              pattern
                              form-id)
        cloned-form (d/pull (d/db pm2-7713-2-conn)
                            pattern
                            form-id)]
    (data/diff original-form cloned-form))

  ;; =>

  ({:workflow.form/type #:db{:id 79164837200619},
    :db/id 92358976736679,
    :workflow.form/last-modified-by #:db{:id 83562883712242},
    :workflow.form/sectionmaps
    [{:form.sectionmap/section
      {:workflow.section/fieldmaps
       [{:workflow.fieldmap/triggers
         [{:workflow.trigger/payload-data
           {:trigger.payload/display-field #:db{:id 83562883712269},
            :db/id 96757023247824},
           :workflow.trigger/conditions
           [{:trigger.condition/operator #:db{:id 83562883712884},
             :trigger.condition/value
             {:field/defn #:db{:id 83562883712270}, :db/id 79164837203376},
             :db/id 79164837203375}],
           :workflow.trigger/action #:db{:id 83562883712894},
           :db/id 79164837203374}],
         :workflow.fieldmap/field #:db{:id 83562883712270},
         :db/id 79164837203373}
        {:workflow.fieldmap/field #:db{:id 83562883712269},
         :db/id 79164837203378}],
       :db/id 79164837203372},
      :db/id 92358976736683}],
    :workflow.form/created-by #:db{:id 83562883712242}}
   {:workflow.form/type #:db{:id 87960930222827},
    :db/id 96757023247765,
    :workflow.form/last-modified-by #:db{:id 96757023245554},
    :workflow.form/sectionmaps
    [{:form.sectionmap/section
      {:workflow.section/fieldmaps
       [{:workflow.fieldmap/triggers
         [{:workflow.trigger/payload-data
           {:trigger.payload/display-field #:db{:id 96757023245581},
            :db/id 96757023247815},
           :workflow.trigger/conditions
           [{:trigger.condition/operator #:db{:id 87960930223988},
             :trigger.condition/value
             {:field/defn #:db{:id 96757023245582}, :db/id 74766790692254},
             :db/id 74766790692253}],
           :workflow.trigger/action #:db{:id 87960930223998},
           :db/id 74766790692252}],
         :workflow.fieldmap/field #:db{:id 96757023245582},
         :db/id 74766790692251}
        {:workflow.fieldmap/field #:db{:id 96757023245581},
         :db/id 74766790692256}],
       :db/id 74766790692250},
      :db/id 96757023247769}],
    :workflow.form/created-by #:db{:id 96757023245554}}
   #:workflow.form{:type #:db{:ident :form.type/client},
                   :created-on #inst "2021-05-06T00:26:33.804-00:00",
                   :name "PM2-7713",
                   :last-modified-on #inst "2021-05-06T00:34:17.826-00:00",
                   :inactive? false,
                   :sectionmaps
                   [#:form.sectionmap{:section
                                      #:workflow.section{:id
                                                         #uuid "11c92191-16e0-433c-997c-05806f9c7a73",
                                                         :type
                                                         #:db{:id 74766790688874,
                                                              :ident
                                                              :section/input},
                                                         :fieldmaps
                                                         [#:workflow.fieldmap{:id
                                                                              #uuid "8ae9618a-9cfe-49a7-a82e-06a8810b15b9",
                                                                              :column
                                                                              1,
                                                                              :required?
                                                                              false,
                                                                              :triggers
                                                                              [#:workflow.trigger{:conditions
                                                                                                  [#:trigger.condition{:operator
                                                                                                                       #:db{:ident
                                                                                                                            :trigger.operator/equals},
                                                                                                                       :value
                                                                                                                       #:field{:long-value
                                                                                                                               1,
                                                                                                                               :id
                                                                                                                               #uuid "02869083-4376-4128-9fca-43a061ee2da4"},
                                                                                                                       :ordinal
                                                                                                                       1,
                                                                                                                       :id
                                                                                                                       #uuid "d87e5d02-7daa-40ad-9c0b-a9ef7df7f874"}],
                                                                                                  :id
                                                                                                  #uuid "38af4a71-adda-423b-bb65-c63e6082070b",
                                                                                                  :action
                                                                                                  #:db{:ident
                                                                                                       :trigger.action/display-field}}],
                                                                              :ordinal
                                                                              1}
                                                          #:workflow.fieldmap{:id
                                                                              #uuid "20eb00bb-0b8b-4d15-ada4-9fbf93cb2ae3",
                                                                              :column
                                                                              1,
                                                                              :required?
                                                                              false,
                                                                              :hidden?
                                                                              true,
                                                                              :ordinal
                                                                              2}],
                                                         :name "section 1"},
                                      :ordinal 1}],
                   :id #uuid "d0d3b2c4-c69c-43ff-bc3b-b4eb50ed264e"})

  )


(def provider-employment-initiation-form-template-id
  [:workflow.form/id #uuid "7419cbe7-d25a-4303-9846-cb2c4e6b2b34"])

(def baseline-dbn "capitalhsi-baseline")
(def training-dbn "capitalhsi-training")

(def pattern
  '[* {:workflow.form/sectionmaps
       [* {:form.sectionmap/section
           [* {:workflow.section/fieldmaps
             [* :workflow.fieldmap/field
              :workflow.fieldmap/ordinal
              :workflow.fieldmap/id
              :workflow.fieldmap/triggers
              :workflow.fieldmap/hidden?]}]}]}])

(defn strip-eids
  "remove all :db/id from a nested data structure

  useful for getting a more meaningful diff across data that has been cloned,
  since we wouldn't expect the eids to match anyway"
  [form]
  (w/postwalk
   (fn [form]
            (if (map? form) (dissoc form :db/id) form))
          form))

(comment

  (def prod-client (db-util/prod-client!))

  (def baseline-conn (d/connect prod-client {:db-name baseline-dbn}))
  (def training-conn (d/connect prod-client {:db-name training-dbn}))

  @(def all-production-databases
     (d/list-databases prod-client {}))

  @(def baseline-form-template
    (let [conn (d/connect prod-client {:db-name baseline-dbn})
          db   (d/db conn)]
      (d/pull db pattern provider-employment-initiation-form-template-id)))

  @(def training-form-template
    (let [conn (d/connect prod-client {:db-name training-dbn})
          db (d/db conn)]
      (d/pull db pattern provider-employment-initiation-form-template-id)))

  @(def diff-baseline-training
     (let [[things-only-in-a things-only-in-b _ :as diff]
           (data/diff (strip-eids baseline-form-template)
                      (strip-eids training-form-template))]
       [things-only-in-a things-only-in-b]))
  ;; => [nil nil]

  ;; so right now they're identical

  (d/q '[:find (pull ?e [*])
         :where
         [?e :workflow.fieldmap/id #uuid "cc771d70-a600-4537-a0ee-85f112408995"]]
       (d/db baseline-conn))

  @(def baseline-form-template-history-arbitrary-fieldmap
    (let [conn (d/connect prod-client {:db-name baseline-dbn})
          history-db (d/history (d/db conn))]
      (d/q '[:find ?tx ?val ?op
             :where
             [?fielmap :workflow.fieldmap/hidden? ?val ?tx ?op]
             [?fielmap :workflow.fieldmap/id #uuid "cc771d70-a600-4537-a0ee-85f112408995"]
             ]
           history-db)))
  ;; => [[13194139534236 true true]]

  @(def training-form-template-history-arbitrary-fieldmap
     (let [conn (d/connect prod-client {:db-name training-dbn})
           history-db (d/history (d/db conn))]
       (d/q '[:find ?tx ?val ?op
              :where
              [?fielmap :workflow.fieldmap/hidden? ?val ?tx ?op]
              [?fielmap :workflow.fieldmap/id #uuid "cc771d70-a600-4537-a0ee-85f112408995"]
              ]
            history-db)))
  ;; => [[13194139534238 true true]]

  @(def training-form-template-history-all-fieldmaps-for-first-section
     (let [history-db (d/history (d/db training-conn))]
       (->> 
        (d/q '[:find ?tx ?val ?op ?workflow-fieldmap-id
               :keys tx val op workflow-fieldmap-id
               :in $ ?section
               :where
               [?fieldmap :workflow.fieldmap/hidden? ?val ?tx ?op]
               [?fieldmap :workflow.fieldmap/id ?workflow-fieldmap-id]
               [?section :workflow.section/fieldmaps ?fieldmap]]
             history-db 83562883715272)
        (group-by :tx))))

  (d/pull (d/db training-conn)
          '[* {:transaction/user-op [* {:user-op/who [:user/email :user/name]}]}]
          13194139535430
          )

  (map #(d/pull (d/db training-conn)
                '[* {:transaction/user-op [* {:user-op/who [:user/email :user/name]}]}] %)
       [13194139538714 13194139534238 13194139535429 13194139535430 13194139534238
        13194139538714 13194139538709 13194139538709 13194139534238 13194139538709 ])

  ;; [[13194139538714 true true #uuid "ff1ef28f-f05c-4a5e-9b6f-65e9adf173a3"]
  ;;  [13194139534238 true true #uuid "883f3549-75ae-4995-99ec-888122025484"]
  ;;  [13194139535429 true true #uuid "2daad32b-97c0-4107-9fa8-080e9013d4a9"]
  ;;  [13194139535430 true false #uuid "b70973b2-6075-4bc5-bda6-6e467b5abbd3"]
  ;;  [13194139534238 true true #uuid "cc771d70-a600-4537-a0ee-85f112408995"]
  ;;  [13194139538714 true true #uuid "d352eef9-469a-4033-9d64-72a183642eb8"]
  ;;  [13194139538709 true true #uuid "c3ecdfba-51d5-46b4-b8e2-12c85cdbb159"]
  ;;  [13194139538709 true true #uuid "cbe07d36-a2a1-4bc9-ba12-f17300146bd9"]
  ;;  [13194139534238 true true #uuid "b70973b2-6075-4bc5-bda6-6e467b5abbd3"]
  ;;  [13194139538709 true true #uuid "4b134f46-42bc-4138-ae68-c5e3c4a9b913"]]

  @(def many-eids-associated-with-form-in-training
     (let [sectionmaps (->> training-form-template
                            :workflow.form/sectionmaps
                            (map :db/id))
           sections (->> training-form-template
                         :workflow.form/sectionmaps
                         (map :form.sectionmap/section)
                         (map :db/id))
           fieldmaps (->> training-form-template
                          :workflow.form/sectionmaps
                          (map :form.sectionmap/section)
                          (mapcat :workflow.section/fieldmaps)
                          (map :db/id))]
       (set (flatten [sectionmaps sections fieldmaps]))))

  @(def transactions-associated-with-many-entities-associated-with-the-form-in-training
    (d/q '[:find (pull ?t [* {:transaction/user-op [*]}]) ?eid
           :in $ [?eid ...]
           :where
           [?eid ?a ?v ?t]]
         (d/db training-conn) many-eids-associated-with-form-in-training))
  ;; i don't see non-baseline `:user-op/where.path`
  ;; i only see baseline. which i take to mean that the work was done in baseline, via ui
  ;; then when we cloned this, we carried fwd the orig `:transaction/user-op`
  ;; this surprises me, because i thought users were fixing this post clone, in training
  ;; and so we'd see traces of "-training" user ops
 
  

  )
