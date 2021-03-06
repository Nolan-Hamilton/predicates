(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn less-than [n]
  (fn [k] (< k n)))

(defn equal-to [n]
  (fn [k] (== k n)))

(defn set->predicate [a-set]
  (fn [x]
    (if (contains? a-set x)
      true
      false)))

(defn pred-and [pred1 pred2]
  (fn [x]
    (if (and (pred1 x) (pred2 x))
      true
      false)))

(defn pred-or [pred1 pred2]
  (fn [x]
    (if (or (pred1 x) (pred2 x))
      true
      false)))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
  (if (or (empty? string) 
          (nil? string) 
          (every? whitespace? string))
    true
    false))

;;------------------------------------------------------------------
;; These are book objects used for testing in REPL
;;------------------------------------------------------------------
;(def china {:name "China Miéville", :birth-year 1972})
;(def octavia {:name "Octavia E. Butler"
;              :birth-year 1947
;              :death-year 2006})
;(def kay {:name "Guy Gavriel Kay" :birth-year 1954})
;(def dick {:name "Philip K. Dick", :birth-year 1928, :death-year 1982})
;(def zelazny {:name "Roger Zelazny", :birth-year 1937, :death-year 1995})
;
;(def authors #{china, octavia, kay, dick, zelazny})
;
;(def cities {:title "The City and the City" :authors #{china}
;             :awards #{:locus, :world-fantasy, :hugo}})
;(def wild-seed {:title "Wild Seed", :authors #{octavia}})
;(def lord-of-light {:title "Lord of Light", :authors #{zelazny}
;                    :awards #{:hugo}})
;(def deus-irae {:title "Deus Irae", :authors #{dick, zelazny}})
;(def ysabel {:title "Ysabel", :authors #{kay}, :awards #{:world-fantasy}})
;(def scanner-darkly {:title "A Scanner Darkly" :authors #{dick}})
;
;(def books #{cities, wild-seed, lord-of-light,
;             deus-irae, ysabel, scanner-darkly})
;;------------------------------------------------------------------
;;------------------------------------------------------------------

(defn has-award? [book award]
  (if (contains? (get book :awards) award)
    true
    false))

;; Predicate used in HAS-ALL-THE-AWARDS?
(defn has-this-award? [book]
  (fn [award]
    (if (contains? (get book :awards) award)
      true
      false)))

(defn HAS-ALL-THE-AWARDS? [book awards]
  (every? (has-this-award? book) awards))

(defn my-some [pred a-seq]
  (if (not (empty? (filter pred a-seq)))
    (pred (first (filter pred a-seq)))
    (first '(falsey))))


(defn my-every? [pred a-seq]
  (if (= a-seq (filter pred a-seq))
    true
    false))

(defn prime? [n]
  (let [pred (fn [x]
               (if (= (mod n x) 0)
                 true
                 false))]
    (not (some pred (range 2 n)))))
;^^
