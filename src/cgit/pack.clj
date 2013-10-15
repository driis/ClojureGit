(ns cgit.pack
  (:require [cgit.io :as io]))

(def magic 4285812579)
(def required-version 2)

(defn valid-pack? [buffer]
  (let [header (io/read-uint buffer)
        version (io/read-uint (drop 4 buffer))]
    (and
      (= magic header)
      (= required-version version))))