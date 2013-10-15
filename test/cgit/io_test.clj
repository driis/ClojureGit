(ns cgit.io-test
  (:require [clojure.test :refer :all]
            [cgit.io :refer :all]))

(deftest i-can-get-blob-path
  (is (=
        ".git/objects/47/a59d0ad0cc623199741d6f98cf3bc3f0e473b2"
        (path-from-hash "47a59d0ad0cc623199741d6f98cf3bc3f0e473b2"))))

(deftest i-can-read-a-file
  (is (> (count (read-file ".git/objects/pack/pack-b7d376ad24ea19fb93f5429eb2c6f190002f5fce.idx")) 5000)))

(deftest i-can-read-integers
  (is (= 2 (read-uint [0 0 0 2])))
  (is (= 512 (read-uint [0 0 2 0])))
  (is (= 515 (read-uint [0 0 2 3])))
  (is (= 4285812579 (read-uint [-1 116 79 99]))))

(deftest read-int-takes-first-4-bytes
  (is (= 512 (read-uint [0 0 2 0 17 12])))
  (is (= 515 (read-uint [0 0 2 3 91 30]))))