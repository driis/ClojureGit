(ns cgit.parse
  (:require [cgit.util.zip :as zip]))

(defn- get-type [bytes]
  (apply str (take-while #(not (= \space %)) (map char bytes))))

(defn- get-length [bytes]
  (let [length-str (drop-while #(not (= \space (char %))) (take-while #(not (= 0 %)) bytes))]
    (read-string (apply str (map char length-str)))))

(defn- get-content [bytes]
  (drop 1 (drop-while #(not (= 0 %)) bytes)))

(defn get-content-string [blob]
  (apply str (map char (:content blob))))

(defn parse-object [bytes]
  {:type (get-type bytes)
   :length (get-length bytes)
   :content (get-content bytes)})

(defn get-object [hash]
  (let [bytes (zip/unzip-blob hash)]
    (parse-object bytes)))
