(ns web-card.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [web-card.core :refer [app]]
            [clojure.string :as str]))

(deftest test-app
  (testing "/validate-card-number: valid"
    (let [card-number "4539 1488 0343 6467"
          req {:request-method :post
               :uri "/validate-card-number"
               :form-params {"card-number" card-number}}
          resp (app req)]
      (is (str/includes? resp (str card-number " is valid")))))
  (testing "/validate-card-number: invalid card"
    (let [card-number "4539 1488 0343 6468"
          req {:request-method :post
               :uri "/validate-card-number"
               :form-params {"card-number" card-number}}
          resp (app req)]
      (is (str/includes? resp (str card-number " is invalid"))))))
