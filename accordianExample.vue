 <template>
 <div>
    <div>applsList<p v-text="applsList"></p></div>
   <div>docList<p v-text="docList"></p></div>
   <div>
      <button type="button" @click="fncInit()">로딩</button>
      <p/>
      <input type="inputbox" v-model="searchWord">
      <button type="button" @click="fncSearchData()">검색</button>
   </div>
 <div>
    <div v-for="(data, index) in this.bizData" :key="index">

      <!-- 단일신청건-->
      <template v-if="data.applsId==''">
        <div>  
            <p/>    
            <button type="button" class="container">
            <input type="checkbox" :id="'chk'+data.docId" v-model="docList" :value="data" @change="fncDocChecked(data.applsId)">
            <label :for="'chk'+data.docId"><span v-text="data.docNm"></span></label>
            <p/> 
            </button>
        </div>
      </template> 


      <!-- 묶음건수-->
      <template v-if="data.applsId!='' && data.applsIndex == 0">
        <div><p/>
          <button type="button" class="container" >
          <input type="checkbox" :id="'chkA'+data.applsId" v-model="applsList" :value="data" @change="fncApplsChecked(data.applsId)">
          <label :for="'chkA'+data.applsId"><span v-text="'묶음신청건수:'+data.applsCnt"></span></label>
          <p/> 
    
          </button>
        </div>
      </template>
      
      <!-- 묶음신청건-->
      <template v-if="data.applsId!=''">
        <div>  
            <p/>    
            <button type="button" class="box">
            <input type="checkbox" :id="'chk'+data.docId" v-model="docList" :value="data" @change="fncDocChecked(data.applsId)">
            <label :for="'chk'+data.docId"><span v-text="data.docNm"></span></label>
            <p/> 
            </button>
        </div>
      </template>

    </div>

  </div>
  </div>
 </template>

<script>

  // 초기화
  //this.fncInit();
 

export default {
  name: 'App',

   data () {
      return {
        data: "hi TEST", // [데이터 정의]  
        bizData:[],         
        docList:[],  
        tempList:[], 
        applsList:[],
        tempApplsList:[], 
        searchWord:"검색하세요.", 
      }
    },
    methods:{

      fncInit:function(){

        var output= require('./test.json'); 
        this.bizData = [];

        for(var i=0;i<output.proofs.length;i++){  
          this.bizData[i] = output.proofs[i];
        } 
      },

      fncApplsChecked:function(aId){
        
        var irow = 0;
        var allChecked = false;
        this.tempList = [];      

        // docList에서 해당 applsId 가진 row 모두 지워서 -> tempList 담기(초기화)
        for(var i=0;i<this.docList.length;i++){     
          if(this.docList[i] == null || this.docList[i].applsId == aId){
            irow--;            
          } else {            
            this.tempList[irow] = this.docList[i];          
          }
          irow++;        
        } 

        // applsList 전부체크인지 확인
        for(var j=0;j<this.applsList.length;j++){
          if(this.applsList[j] != null && this.applsList[j].applsId==aId){
              allChecked = true;   
            }
          }
        
        // this.bizData에서 applsId가진 row -> tempList 추가
        if(allChecked){
          for(var k=0;k<this.bizData.length;k++){         
            if(this.bizData[k].applsId==aId){          
                this.tempList[irow] = this.bizData[k]; 
                irow++;  
            }   
          }
        }

        this.docList = [];
        this.docList = this.tempList;

      }, 

      fncDocChecked: function(aId){

        var aCnt = 0;
        var irow = 0;
        var allChecked = false;
        this.tempApplsList = [];   
        
        // 단일신청건은 PASS
        if(aId==""){
          return;
        }

        // applsList에서 해당 applsId 가진 row 지워서 -> tempApplsList 담기(초기화)
        for(var i=0;i<this.applsList.length;i++){     
          if(this.applsList[i] == null || this.applsList[i].applsId == aId){
            irow--;            
          } else {            
            this.tempApplsList[irow] = this.applsList[i];          
          }
          irow++;        
        }  

        // docList에서 전부체크인지 확인
        for(var j=0;j<this.docList.length;j++){
          if(this.docList[j] != null && this.docList[j].applsId==aId){
              aCnt++; 
              
              // 묶음건수 == 체크된 수
              if(this.docList[j].applsCnt==aCnt){
                allChecked = true;             
              }
            }
          }

          // this.bizData에서 applsId가진 row -> tempApplsList 추가
          if(allChecked){            
            for(var k=0;k<this.bizData.length;k++){         
              if(this.bizData[k].applsId==aId && this.bizData[k].applsIndex=="0"){          
                  this.tempApplsList[irow] = this.bizData[k];  
              }   
            } 
          }
          
          this.applsList = [];
          this.applsList = this.tempApplsList;
      },

      fncSearchData:function(){

        this.fncInit();

        var irow = 0;
        var sWord = this.searchWord;
        var searchList = new Array();        

        // 검색
        this.bizData.filter(function(e){
          
          console.log("sWord", sWord);
          if(e.docNm.indexOf(sWord)!=-1){
              searchList[irow] = e;
              irow++;
          }
        });

        this.bizData = [];
        this.bizData = searchList;    

      }, 

      fncDeleteCert(){
        var apList = "{[{'packageId':'111'},{'packageId':'112'},{'packageId':'1113'}]}";
        var docList = "{[{'packageId':'111','docId':'10'},{'packageId':'111','docId':'11'},{'packageId':'112','docId':'12'},{'packageId':'112','docId':'13'},{'packageId':'112','docId':'14'}{'packageId':'1113','docId':'15'}]}";
        //var tmpArr = [];


        for(var i=0;i<apList.length;i++){

          console.log("apList",apList);
          console.log("docList",docList);
        }


      },
    }
  }


</script>

 

<style>
 .container {width:300px;
height:50px;
background:skyblue;}
.box {width:300px;
height:50px;
background:white;} 
</style>
