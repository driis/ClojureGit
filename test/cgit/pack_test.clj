(ns cgit.pack-test
  (:require [clojure.test :refer :all]
            [cgit.pack :refer :all]
            [cgit.io :refer :all]))

(def index-file ".git/objects/pack/pack-b7d376ad24ea19fb93f5429eb2c6f190002f5fce.idx")

(deftest a-index-file-is-valid
  (let [buffer (read-file index-file)]
    (is (valid-pack? buffer))))

(deftest a-random-file-is-invalid
  (let [buffer (read-file "readme.md")]
    (is (not (valid-pack? buffer)))))

(deftest i-can-read-item-count-from-index-file
  (let [buffer (read-file index-file)
        length (index-length buffer)]
    (is (and (> length  100)
             (< length 5000)))))