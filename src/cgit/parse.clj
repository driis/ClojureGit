(ns cgit.parse
  (:require [cgit.util.zip :as zip]))

;; Helper functions
(defn- get-type [bytes]
  (apply str (take-while #(not (= \space %)) (map char bytes))))

(defn- get-length [bytes]
  (let [length-str (drop-while #(not (= \space (char %))) (take-while #(not (= 0 %)) bytes))]
    (read-string (apply str (map char length-str)))))

(defn- get-content [bytes]
  (drop 1 (drop-while #(not (= 0 %)) bytes)))

;; Parse raw blob
(defn get-content-string [blob]
  (apply str (map char (:content blob))))

(defn parse-blob [bytes]
  {:type (get-type bytes)
   :length (get-length bytes)
   :content (get-content bytes)})

(defn get-blob [hash]
  (let [bytes (zip/unzip-blob hash)]
    (parse-blob bytes)))

;; Parse object types
(defn parse-commit [blob]
  blob)