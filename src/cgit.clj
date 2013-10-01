(ns cgit
  (:require [cgit.util.zip :as zip]))

(defn- get-type [bytes]
  (apply str (take-while #(not (= \space %)) (map char bytes))))

(defn- get-length [bytes]
  (let [length-str (drop-while #(not (= \space (char %))) (take-while #(not (= 0 %)) bytes))]
    (read-string (apply str (map char length-str)))))

(defn parse-object [bytes]
  {:type (get-type bytes)
   :length (get-length bytes)})

(defn get-object [hash]
  (let [bytes (zip/unzip-blob hash)]
    (parse-object bytes)))

(defn cat-file
  "Returns the content of a git object"
  [hash]
  (get-object hash)
)
