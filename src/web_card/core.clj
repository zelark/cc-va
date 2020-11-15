(ns web-card.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.params]
            [ring.middleware.content-type]
            [ring.middleware.resource]
            [ring.util.response :as response]
            [web-card.page :as page]
            [web-card.credit-card :as cc]))

(defn handler [request]
  (condp = (:uri request)
    ;; index
    "/"
    (response/content-type
     (response/response (page/index))
     "text/html; charset=utf-8")
    "/validate-card-number"
    (let [card-number (get-in request [:form-params "card-number"])
          valid? (and card-number (cc/valid? card-number))]
      (response/content-type
       (response/response
        (page/index {:card-number card-number
                     :valid? valid?}))
       "text/html; charset=utf-8"))
    ;; favicon
    "/favicon.ico"
    (response/resource-response "favicon.ico" {:root "public"})
    ;; styles just for reset
    "/style.css"
    (response/resource-response "style.css" {:root "public"})
    ;; 404
    (response/content-type
     (response/not-found "There is nothing here. 🤪")
     "text/html; charset=utf-8")))

(def app
  (-> #'handler
      ring.middleware.content-type/wrap-content-type
      ring.middleware.params/wrap-params))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT")))]
    (jetty/run-jetty #'app {:port port :join? false})))


(comment
  (-main 3000))

