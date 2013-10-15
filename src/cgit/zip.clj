(ns cgit.zip
  (:require [clojure.string :as str]
            [cgit.io :refer :all])
  (:import (java.io FileInputStream IOException FileNotFoundException)
           (java.util.zip InflaterInputStream)))


(defn unzip-file [file]
  (let [inflater (InflaterInputStream. (FileInputStream. file))]
    (let [result (read-full-stream inflater)]
      (.close inflater)
      result)))

(defn unzip-blob [hash]
  (unzip-file (path-from-hash hash)))
