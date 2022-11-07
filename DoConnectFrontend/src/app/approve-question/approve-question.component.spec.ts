import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveQuestionComponent } from './approve-question.component';

describe('ApproveQuestionComponent', () => {
  let component: ApproveQuestionComponent;
  let fixture: ComponentFixture<ApproveQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApproveQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
