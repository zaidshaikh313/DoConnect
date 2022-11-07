import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveAnswerComponent } from './approve-answer.component';

describe('ApproveAnswerComponent', () => {
  let component: ApproveAnswerComponent;
  let fixture: ComponentFixture<ApproveAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveAnswerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApproveAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
