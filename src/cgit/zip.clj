(ns cgit.zip
  (:require [clojure.string :as str]
            [cgit.io :refer :all]
            [clojure.java.io :as cio])
  (:import (java.util.zip InflaterInputStream)))


(defn unzip-file [file]
  (let [inflater (InflaterInputStream. (cio/input-stream file))]
    (let [result (read-full-stream inflater)]
      (.close inflater)
      result)))

(defn unzip-blob [hash]
  (unzip-file (path-from-hash hash)))
