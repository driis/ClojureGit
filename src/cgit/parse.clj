(ns cgit.parse
  (:require [cgit.util.zip :as zip]
            [clojure.string :as str]
            [clojure.java.io]))

;; Helper functions
(defn- get-type [bytes]
  (apply str (take-while #(not (= \space %)) (map char bytes))))

(defn- get-length [bytes]
  (let [length-str (drop-while #(not (= \space (char %))) (take-while #(not (= 0 %)) bytes))]
    (read-string (apply str (map char length-str)))))

(defn- get-content [bytes]
  (drop 1 (drop-while #(not (= 0 %)) bytes)))

(defn- lines [blob]
  (let [rdr (clojure.java.io/reader (into-array Byte/TYPE (:content blob)))]
    (line-seq rdr)))

(defn- parse-time [time-parts]
  {:timestamp (Integer/parseInt (first time-parts))
   :utc-offset (Integer/parseInt (second time-parts))})

(defn- parse-email [email-parts]
  (apply str (butlast (rest (first email-parts)))))

(defn- parse-name [name-parts]
  (str/join \space name-parts))

(defn- parse-author [author-parts]
  (let [author-parts (partition-by #(= \< (first %)) author-parts)]
    {:name (parse-name (first author-parts))
     :email (parse-email (second author-parts))
     :authored-time (parse-time (nth author-parts 2))}))

(defn- parse-line [line]
  (let [parts (str/split line #"\s")]
    (let [key (first parts)]
      (let [value (case key
                    "author" (parse-author (rest parts))
                    "committer" (parse-author (rest parts))
                    (last parts))]
        {(keyword key) value}))))

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
  (let [parts (split-with #(not (= "" %)) (lines blob))]
    (let [comment (str/join "\n" (drop 1 (last parts)))
          values (map parse-line (first parts))]
      (into {:comment comment} values))))

(defn get-commit [hash]
  (parse-commit (get-blob hash)))