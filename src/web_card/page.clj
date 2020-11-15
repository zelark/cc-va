(ns web-card.page
  (:require [hiccup.page :as hiccup.page]))

(defn index
  ([] (index {}))
  ([{:keys [card-number valid?]}]
   (hiccup.page/html5 {:lang "en"}
    [:head
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1.0"}]
     [:title "CC Va"]
     (hiccup.page/include-css "style.css")]
    [:body {:style "font-size: 150%"}
     [:div {:style "margin: 20px 30px"}
      [:form {:action "/validate-card-number" :method "post"}
       [:label {:for "card-number" :style "margin: 4px"} "Card Number"]
       [:input.card-number {:name "card-number" :value card-number :type "text" :style "margin: 4px"}]
       [:input {:type "submit" :value "Validate" :style "margin: 4px"}]]
      (when card-number
        [:p {:style "margin: 16px 4px"}
         (if valid?
           [:strong {:style "color: #357266"} card-number " is valid, 🤑"]
           [:strong {:style "color: #990011"} card-number " is invalid, 🥵"])])]])))
