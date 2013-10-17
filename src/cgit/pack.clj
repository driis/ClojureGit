(ns cgit.pack
  (:require [cgit.io :as io]))

(def header-length 2)
(def fanout-length 256)

(def magic 4285812579)
(def required-version 2)

(defn valid-pack? [buffer]
  "Determines whether the bytes in buffer represents a valid git pack index file."
  (let [[header version rest] (io/read-uints buffer)]
    (and
      (= magic header)
      (= required-version version))))

(defn index-length [buffer]
  "Given a buffer that is the sequence of bytes in a git pack index file, returns the number of objects in that index."
  (let [offset (* 4 (- (+ header-length fanout-length) 1))]
    (io/read-uint (drop offset buffer))))