package testgroovy

import groovy.transform.TypeChecked

/**
 * @Author wenbaoxie* @Date 2020/10/17
 */
class Test {
    def static add(num) {
        def sum = 0
        sum += num
        return sum
    }

    def static addByClosure(init) {
        def addInner = {
            inc ->
                init += inc
                init
        }
        return addInner
    }

    def static add() {
        1
    }

    public static void main(String[] args) {
        def name = '11'
        println "11 $name"
        println ''' dfafd
dafdsfdasfdsalfd
fdasfdsa;fdlajfkd
dfasffdfd'''
        println add(1)
        println "++++++++++++++++++++++++++++++++++++++"
        sc.call()
        sc.setDelegate("ddd")
        sc.call()
        println "++++++++++++++++++++++++++++++++++++++"

        def switchtest = 100
        switch (switchtest) {
            case 100:
                println "100"
                break
            case [1, 2, 43]:
                println "[1,2,43]"
                break
            case [1: 1, 2: 3]:
                println "[1:1,2:3]"
                break
            default:
                println "default"
                break
        }
        def testmap = [1: "one", 2: "two"]
        println testmap.get(10)
        println "++++++++++++++++++++++++++++++++++++++"
        def p = [1, 2, 3, 4, 1, 3, 2]
        def s = p.findAll { x -> x > 2 }
        println p
        def sx = p.sort { a, b -> return -a + b
        }
        println p
        println s

        println "++++++++++++++++++++++++++++++++++++++"
        def a = (1..10)*.multiply(2)
        println a
        println "++++++++++++++++++++++++++++++++++++++"
        (1..10).each { x ->
            print x
        }
        println "++++++++++++++++++++++++++++++++++++++"
        def  mymeth = {e ->println e.class }
        def list = new ObservableList()
        list.addPropertyChangeListener(mymeth)
        list.add(1)
        list.add(1)
        list.add(1)
        list.remove(1)

    }
    static def sc = {
        println "this" + this
        println "owner" + owner
        println "delegate" + delegate

    }
}

@TypeChecked
class te {
    def i = 10.0
}
